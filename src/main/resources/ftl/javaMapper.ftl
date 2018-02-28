package ${mapperPackage};

import ${entityPackage}.${entityName};
import java.util.List;

public interface ${entityName}Mapper {

    int insert(${entityName} ${entityNameLowCase});

    int insertSelective(${entityName} ${entityNameLowCase});

    ${entityName} select(Long id);

    int update(${entityName} ${entityNameLowCase});

    int delete(Long id);

    int updateSelective(${entityName} ${entityNameLowCase});

    void batchUpdate(List<${entityName}> ${entityNameLowCase}List);

}