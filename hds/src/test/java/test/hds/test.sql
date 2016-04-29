select 
c.busi_type,c.busi_mark,c.msg_no,c.biz_serial_no,
c.channel_serial_no,a.fee_category1,a.fee_category2,
c.detail_no,c.trade_date,c.taker_code,c.transferee_code,
c.transferor_code,a.currency,c.orig_busi_type,c.orig_busi_mark,
c.orig_msg_no,c.orig_biz_serial_no,c.orig_taker_code,a.org_code as fee_org_code,
a.org_name as fee_org_name,a.fee_amount, a.result_code as fee_results,a.occur_date
FROM his_fee_jour a,
his_fee_trade_core_process b,
his_fee_trade_core c
WHERE a.serial_no = b.process_id
AND b.fee_trade_sn = c.fee_trade_sn
AND c.taker_code = #{takerCode,jdbcType=VARCHAR}
AND (whereclause)


--交易日期(结束)	start_date	M	HsChar8	his_fee_trade_core trade_date
--交易日期(开始)	start_date	M	HsChar8	his_fee_trade_core trade_date
--发起方机构号	taker_code	M	HsChar32	his_fee_trade_core
--费用大类	fee_category1	M	HsChar10	his_fee_jour
--费用小类	fee_category2	M	HsChar10	his_fee_jour
