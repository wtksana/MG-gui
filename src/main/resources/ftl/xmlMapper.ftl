<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperPackage}.${entityName}Mapper">

    <resultMap id="BaseResultMap" type="${entityPackage}.${entityName}">
    <#list attrs as item>
        <<#if item.column=="id">id<#else>result</#if> column="${item.column}" jdbcType="${item.jdbcType}" property="${item.property}"/>
    </#list>
    </resultMap>

    <sql id="Base_Column_List">
        <#list attrs as item>
            ${item.column}<#if item_has_next>,</#if>
        </#list>
    </sql>

    <insert id="insert" parameterType="${entityPackage}.${entityName}">
        <selectKey keyProperty="id" order="AFTER" resultType="java.lang.Long">
            SELECT LAST_INSERT_ID()
        </selectKey>
        insert into ${tableName} (
        <include refid="Base_Column_List"/>
        )
        values (
        <#list attrs as item>
            ${r'#'}{${item.property}}<#if item_has_next>,</#if>
        </#list>
        )
    </insert>

    <insert id="insertSelective" parameterType="${entityPackage}.${entityName}">
        <selectKey keyProperty="id" order="AFTER" resultType="java.lang.Long">
            SELECT LAST_INSERT_ID()
        </selectKey>
        insert into ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <#list attrs as item>
            <if test="${item.property} != null">
                ${item.column},
            </if>
            </#list>
        </trim>
        values
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <#list attrs as item>
                <if test="${item.property} != null">
                    ${r'#'}{${item.property}},
                </if>
            </#list>
        </trim>
    </insert>

    <select id="select" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ${tableName}
        where id = ${r'#'}{id}
    </select>

    <update id="update" parameterType="${entityPackage}.${entityName}">
        update ${tableName}
        <set>
        <#list attrs as item>
            ${item.column} = ${r'#'}{${item.property}}<#if item_has_next>,</#if>
        </#list>
        </set>
        where id = ${r'#'}{id}
    </update>

    <delete id="delete">
        delete from ${tableName}
        where id = ${r'#'}{id}
    </delete>

    <update id="updateSelective" parameterType="${entityPackage}.${entityName}">
        update ${tableName}
        <set>
        <#list attrs as item>
            <if test="${item.property} != null">
                ${item.column} = ${r'#'}{${item.property}}<#if item_has_next>,</#if>
            </if>
        </#list>
        </set>
        where id = ${r'#'}{id}
    </update>

    <update id="batchUpdate" parameterType="java.util.List">
        <foreach collection="list" separator=";" item="item">
            update ${tableName}
            <set>
            <#list attrs as item>
                ${item.column} = ${r'#'}{item.${item.property}}<#if item_has_next>,</#if>
            </#list>
            </set>
            where id = ${r'#'}{item.id}
        </foreach>
    </update>

</mapper>