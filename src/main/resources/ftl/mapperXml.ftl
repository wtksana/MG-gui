<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperPackage}.${entityName}Mapper">
    <resultMap id="BaseResultMap" type="${entityPackage}.${entityName}Entity">
        <id column="id" jdbcType="INTEGER" property="id" />
        <result column="username" jdbcType="VARCHAR" property="username" />
        <result column="password" jdbcType="VARCHAR" property="password" />
        <result column="mobile" jdbcType="INTEGER" property="mobile" />
    </resultMap>
    <delete id="deleteByPrimaryKey" parameterType="java.lang.Integer">
        delete from user
        where id = #{id,jdbcType=INTEGER}
    </delete>
    <insert id="insert" parameterType="com.wt.study.spot.domain.User">
        <selectKey keyProperty="id" order="AFTER" resultType="java.lang.Integer">
            SELECT LAST_INSERT_ID()
        </selectKey>
        insert into user (username, password, mobile
        )
        values (#{username,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, #{mobile,jdbcType=INTEGER}
        )
    </insert>
    <update id="updateByPrimaryKey" parameterType="com.wt.study.spot.domain.User">
        update user
        set username = #{username,jdbcType=VARCHAR},
        password = #{password,jdbcType=VARCHAR},
        mobile = #{mobile,jdbcType=INTEGER}
        where id = #{id,jdbcType=INTEGER}
    </update>
    <select id="selectByPrimaryKey" parameterType="java.lang.Integer" resultMap="BaseResultMap">
        select id, username, password, mobile
        from user
        where id = #{id,jdbcType=INTEGER}
    </select>
    <select id="selectAll" resultMap="BaseResultMap">
        select id, username, password, mobile
        from user
    </select>
</mapper>