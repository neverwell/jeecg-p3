SELECT id FROM erp_category AS ec 
where LENGTH(ec.id) = :localCodeLength
and ec.id like CONCAT(:parentCode ,'%')
order by ec.id desc 
limit 1