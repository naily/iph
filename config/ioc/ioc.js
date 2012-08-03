
var ioc = {
    config : {
                type : "org.nutz.ioc.impl.PropertiesProxy",
                fields : {
                        paths : ["db.properties","sysconfig.properties","mail.properties"]
                }
    },
    dao : {
            type : "org.nutz.dao.impl.NutDao",
            args : [{refer:"dataSource"}]
    },
    dataSource : {
            type : "com.alibaba.druid.pool.DruidDataSource",
            events : {
                    depose : 'close'
            },
            fields : {
                    driverClassName : {java: "$config.get('db-driver')"},
                    url : {java: "$config.get('db-url')"},
                    username : {java: "$config.get('db-username')"},
                    password : {java: "$config.get('db-password')"},
                    maxActive : 20
            }
    }
}