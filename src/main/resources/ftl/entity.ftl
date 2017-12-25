package ${entityPackage};

import java.io.Serializable;
<#list imports as item>
${item}
</#list>

public class ${entityName} implements Serializable{
    <#list attrs as item>
    /** ${item.comment} */
    private ${item.javaType} ${item.property};

    </#list>

    /*--------------------getter/setter--------------------*/

    <#list attrs as item>
    public ${item.javaType} get${item.propertyUpCase}() {
        return ${item.property};
    }

    public void set${item.propertyUpCase}(${item.javaType} ${item.property}) {
        this.${item.property} = ${item.property};
    }

    </#list>
    /*--------------------getter/setter--------------------*/

}