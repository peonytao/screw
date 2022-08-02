package com.han.screw.service;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.han.screw.constant.DatabaseEnum;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;

/**
 * 生成数据库文件
 *
 * @author han
 * @date 2022/08/02
 */
public abstract class GenerateTemplate {

    final String driverClassName = "com.mysql.cj.jdbc.Driver";

    final String jdbcUrl = "jdbc:mysql://192.168.0.129:3333/";

    final String username = "root";

    final String password = "123456";

    /**
     * 初始化数据源
     */
    private DataSource initDataSource(String dataBase){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setJdbcUrl(jdbcUrl + dataBase);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        //设置可以获取tables remarks信息
        hikariConfig.addDataSourceProperty("useInformationSchema", "true");
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setMaximumPoolSize(5);
        return new HikariDataSource(hikariConfig);
    }

    /**
     * 文件生成配置
     *
     * @return {@link EngineConfig}
     */
    abstract EngineConfig engineConfig();

    /**
     * 表生成配置
     *
     * @return {@link ProcessConfig}
     */
    private ProcessConfig processConfig(){
        //忽略表
        ArrayList<String> ignoreTableName = new ArrayList<>();

        //忽略表前缀
        ArrayList<String> ignorePrefix = new ArrayList<>();

        //忽略表后缀
        ArrayList<String> ignoreSuffix = new ArrayList<>();

        return ProcessConfig.builder()
                //指定生成逻辑、当存在指定表、指定表前缀、指定表后缀时，将生成指定表，其余表不生成、并跳过忽略表配置
                //根据名称指定表生成
                .designatedTableName(new ArrayList<>())
                //根据表前缀生成
                .designatedTablePrefix(new ArrayList<>())
                //根据表后缀生成
                .designatedTableSuffix(new ArrayList<>())
                //忽略表名
                .ignoreTableName(ignoreTableName)
                //忽略表前缀
                .ignoreTablePrefix(ignorePrefix)
                //忽略表后缀
                .ignoreTableSuffix(ignoreSuffix).build();
    }


    /**
     * 执行生成，外部调用
     */
    public void execute(){
        var engineConfig = this.engineConfig();
        var processConfig = this.processConfig();
        //遍历生成库文件
        for (DatabaseEnum value : DatabaseEnum.values()) {
            var name = value.getName();
            var dataSource = this.initDataSource(name);
            //设置文件名
            engineConfig.setFileName(name);
            Configuration config = Configuration.builder()
                    //版本
                    .version("1.0.0")
                    //描述
                    .description(name + "文档生成")
                    //数据源
                    .dataSource(dataSource)
                    //生成配置
                    .engineConfig(engineConfig)
                    //生成配置
                    .produceConfig(processConfig)
                    .build();
            //执行生成
            new DocumentationExecute(config).execute();
        }

    }




}
