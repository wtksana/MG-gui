package ${packageName}.${entityName}.domain;

@Entity
@Table(name = "${tableName}")
public class ${entityName} {
<#if attrs?exists>
    <#list attrs as item>
    /* ${item.comment} */
    <#if item.name=="id">
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    </#if>
    private ${item.type} ${item.name};
    </#list>
</#if>

/*------------------------------------------*/
<#if attrs?exists>
    <#list attrs as item>

    public ${item.type} get${item.nameUpCase}() {
        return ${item.name};
    }

    public void set${item.nameUpCase}(${item.type} ${item.name}) {
        this.${item.name} = ${item.name};
    }
    </#list>
</#if>
}