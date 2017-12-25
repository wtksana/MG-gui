package ${mapperPackage};

import ${entityPackage}.${entityName};

public interface ${entityName}Mapper {

    int insert(${entityName} ${entityNameLowCase});

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKey(Long id);

    ${entityName} selectByPrimaryKey(Long id);

}