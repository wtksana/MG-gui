package ${packageName}.${entityName}.dao.impl;

import ${packageName}.${entityName}.dao.${entityName}Dao;
import ${packageName}.${entityName}.domain.${entityName};
import org.springframework.stereotype.Repository;

@Repository("${entityNameLowCase}Dao")
public class ${entityName}DaoImpl extends BaseDaoImpl<${entityName}> implements ${entityName}Dao {