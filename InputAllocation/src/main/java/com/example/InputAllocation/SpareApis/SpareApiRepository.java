package com.example.InputAllocation.SpareApis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SpareApiRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	@Autowired
	private InputsMailSender inputsMailSender;

	public List<Map<String, Object>> getDivisions(String username) {
		String query = "select distinct(divisioncode),divisionname from tbl_product_sales";
		return jdbcTemplate.queryForList(query);
	}

	public List<Map<String, Object>> getHeaQuarters(Map<String, Object> data) {
		String query = "select distinct(hqcode),hqname from tbl_product_sales where divisioncode=? and year(invoicedate)=? and month(invoicedate)=?";
		return jdbcTemplate.queryForList(query,
				new Object[] { data.get("divisioncode"), data.get("year"), data.get("month") });
	}

	public List<SalePojo> getSalesData(Map<String, Object> data) {
		LocalDate date = LocalDate.of((int) data.get("year"), (int) data.get("month"), 1);
		LocalDate lastMonthFirst = date.minusMonths(1);
		LocalDate lastMonthLast = lastMonthFirst.withDayOfMonth(lastMonthFirst.lengthOfMonth());
		LocalDate previousMonthFirst = date.minusMonths(2);
		LocalDate previousMonthLast = previousMonthFirst.withDayOfMonth(previousMonthFirst.lengthOfMonth());

		StringBuffer query = new StringBuffer();

		query.append(" SELECT hqcode,hqname,materialcode,materialname, ");
		query.append(" SUM(CASE ");
		query.append(" WHEN invoicedate BETWEEN '" + previousMonthFirst + "' and '" + previousMonthLast
				+ "' THEN saleamount ");
		query.append(" ELSE 0 ");
		query.append(" END) AS PreviousMonthSales, ");

		query.append(" SUM(CASE ");
		query.append(" WHEN invoicedate BETWEEN '" + lastMonthFirst + "' and '" + lastMonthLast + "' THEN saleamount ");
		query.append(" ELSE 0 ");
		query.append(" END) AS LastMonthSales, ");

		query.append(" SUM(CASE ");
		query.append(" WHEN invoicedate BETWEEN '" + lastMonthFirst + "' and '" + lastMonthLast + "' THEN tse ");
		query.append(" ELSE 0 ");
		query.append(" END) AS tse, ");

		query.append(" ifnull(SUM(CASE ");
		query.append(" WHEN invoicedate BETWEEN '" + lastMonthFirst + "' and '" + lastMonthLast + "' THEN saleamount ");
		query.append(" ELSE 0 ");
		query.append(" END)/ ");
		query.append(" SUM(CASE ");
		query.append(" WHEN invoicedate BETWEEN '" + lastMonthFirst + "' and '" + lastMonthLast + "' THEN tse ");
		query.append(" ELSE 0 ");
		query.append(" END),0) as phpm ");

		query.append(" from input_allocation.tbl_product_sales ");
		query.append(" where divisioncode=? and hqcode=? and invoicedate between '" + previousMonthFirst + "' and '"
				+ lastMonthLast);
		query.append("' group by hqcode,materialcode; ");

		return jdbcTemplate.query(query.toString(), (rs, rowNum) -> {
			SalePojo sp = new SalePojo();
			sp.setHqcode(rs.getString("hqcode"));
			sp.setHqname(rs.getString("hqname"));
			sp.setMaterialcode(rs.getString("materialcode"));
			sp.setMaterialname(rs.getString("materialname"));
			sp.setPreviousmonthsales(rs.getDouble("previousmonthsales"));
			sp.setPreviousmonth(previousMonthFirst.getMonthValue());
			sp.setLastmonthsales(rs.getDouble("lastmonthsales"));
			sp.setLastmonth(lastMonthFirst.getMonthValue());
			sp.setTsecount(rs.getInt("tse"));
			sp.setPhpm(rs.getDouble("phpm"));
			return sp;
		}, new Object[] { data.get("divisioncode"), data.get("hqcode") });
	}

	public List<Map<String, Object>> getMaterialData(Map<String, Object> data) {
		StringBuffer query = new StringBuffer();
		query.append(" SELECT therapeuticname,brandname,customername,customercode,materialcode, ");
		query.append(" materialname,sum(saleqty) saleqty,sum(saleamount) saleamount,sum(returnqty) returnqty, ");
		query.append(" sum(returnamount) returnamount,sum(netqty) netqty,sum(netamount) netamount,sum(tse) tse ");
		query.append(" from tbl_product_sales ");
		query.append(" where divisioncode=? and hqcode=? and materialcode=? ");
		query.append(" and year(invoicedate)=? and month(invoicedate)=? ");
		query.append(" group by materialcode,customercode ");

		return jdbcTemplate.queryForList(query.toString(), new Object[] { data.get("divisioncode"), data.get("hqcode"),
				data.get("materialcode"), data.get("year"), data.get("month") });
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> allocateInputsPerHq(Map<String, Object> data) {
		String query = "select EmpCode,EmpName,HQCode,HQName from tbl_sale_executives where hqcode in(:hqlist) and dno=1";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("hqlist", data.get("hqlist"));

		List<SaleExecutives> sel = namedJdbcTemplate.query(query, params, (rs, rowNow) -> {
			SaleExecutives se = new SaleExecutives();
			se.setEmpCode(rs.getString("EmpCode"));
			se.setEmpName(rs.getString("EmpName"));
			se.setHQCode(rs.getString("HQCode"));
			return se;
		});

		Map<String, List<SaleExecutives>> map = new HashMap<>();
		for (SaleExecutives se : sel) {
			if (map.get(se.getHQCode()) != null) {
				map.get(se.getHQCode()).add(se);
			} else {
				List<SaleExecutives> l = new ArrayList<>();
				l.add(se);
				map.put(se.getHQCode(), l);
			}
		}

		List<Map<String, Object>> InputsPerHqList = (List<Map<String, Object>>) data.get("data");
		for (Map<String, Object> hqinputsmap : InputsPerHqList) {
			int seCount = map.get(hqinputsmap.get("hqcode").toString()).size();

			for (Map<String, Object> hqinput : (List<Map<String, Object>>) hqinputsmap.get("inputlist")) {
				int quantity = (int) hqinput.get("inputqty");
				int perhead = quantity / seCount;

				for (SaleExecutives se : map.get(hqinputsmap.get("hqcode"))) {
					Map<String, Object> m = new HashMap<>();
					m.put("inputcode", hqinput.get("inputcode"));
					m.put("inputname", hqinput.get("inputname"));
					m.put("inputqty", quantity);
					m.put("allocatedinputqty", perhead);
					m.put("remainqty", quantity - (perhead * seCount));
					se.getInputs().add(m);
				}
			}
		}

		List<Map<String, Object>> allocatedData = new ArrayList<>();
		map.keySet().parallelStream().forEach(hqcode -> {
			Map<String, Object> m = new LinkedHashMap<>();
			m.put("hqcode", hqcode);
			m.put("tseData", map.get(hqcode));
			allocatedData.add(m);
		});
		return allocatedData;

	}

	public List<Map<String, Object>> fetchInputMaterials() {
		return jdbcTemplate.queryForList("SELECT InputId, InputName, AblQty FROM tbl_inputs WHERE status=1001");
	}

	@Transactional(rollbackFor = Throwable.class)
	public int updateInputMaterials(Map<String, Object> data) {
		String query = "update tbl_inputs set ablqty=? where inputid=?";
		String totalQty = "select ablqty from tbl_inputs where inputid=?";

		@SuppressWarnings("unchecked")
		List<Map<String, Object>> inputList = (List<Map<String, Object>>) (data.get("inputslist"));
		int[] updateCounts = jdbcTemplate.execute((Connection con) -> {
			PreparedStatement stmt = con.prepareStatement(query);
			for (Map<String, Object> input : inputList) {
				stmt.setInt(1,
						jdbcTemplate.queryForObject(totalQty, Integer.class, new Object[] { input.get("inputid") })
								- (int) input.get("inputqty"));
				stmt.setString(2, (String) input.get("inputid"));
				stmt.addBatch();
			}
			return stmt.executeBatch();
		});

		int totalRowsUpdated = 0;
		for (int updateCount : updateCounts) {
			totalRowsUpdated += updateCount;
		}

		if (totalRowsUpdated != inputList.size()) {
			throw new RuntimeException("Failed to update inputs quantity");
		}
		return inputList.size();
	}

	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor = Throwable.class)
	public int updateAllocatedInputs(Map<String, Object> data) {
		int divisioncode = (int) data.get("divisioncode");
		int year = (int) data.get("year");
		int month = (int) data.get("month");
		String query = "Insert into tbl_allocated_inputs(divisioncode,hqcode,year,month,empcode,inputid,inputqty) values(?,?,?,?,?,?,?)";

		for (Map<String, Object> hq : (List<Map<String, Object>>) data.get("hqlist")) {
			String hqcode = (String) hq.get("hqcode");
			for (Map<String, Object> se : (List<Map<String, Object>>) hq.get("selist")) {
				String empcode = (String) se.get("empcode");
				List<Map<String, Object>> inputList = (List<Map<String, Object>>) se.get("inputlist");

				int[] updateCounts = jdbcTemplate.execute((Connection con) -> {
					PreparedStatement stmt = con.prepareStatement(query);
					for (Map<String, Object> input : inputList) {
						stmt.setInt(1, divisioncode);
						stmt.setString(2, hqcode);
						stmt.setInt(3, year);
						stmt.setInt(4, month);
						stmt.setString(5, empcode);
						stmt.setString(6, (String) input.get("inputid"));
						stmt.setInt(7, (int) input.get("allocatedinputqty"));
						stmt.addBatch();
					}
					return stmt.executeBatch();
				});

				int totalRowsUpdated = 0;
				for (int updateCount : updateCounts) {
					totalRowsUpdated += updateCount;
				}

				if (totalRowsUpdated != inputList.size()) {
					throw new RuntimeException("Failed to update allocated inputs quantity to se");
				}
			}
		}

		return 0;
	}

	public int mailSender(Map<String, Object> data) throws MessagingException {
		Map<String, Object> mailData = jdbcTemplate.queryForMap("select * from tbl_mail_data");
		return inputsMailSender.sendMail(data, mailData);
	}
}