import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.*;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Generator {
    /**
     * 执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
     *
     * @author zhoubin
     * @since 1.0.0
     */
        /**
         * <p>
         * 读取控制台内容
         * 在控制台输入表名，就会自动生成所需要的文件
         * </p>
         */
        public static String scanner(String tip) {
            Scanner scanner = new Scanner(System.in);
            StringBuilder help = new StringBuilder();
            help.append("请输入" + tip + "：");
            System.out.println(help.toString());
            if (scanner.hasNext()) {
                String ipt = scanner.next();
                if (StringUtils.isNotEmpty(ipt)) {
                    return ipt;
                }
            }
            throw new MybatisPlusException("请输入正确的" + tip + "！");
        }

        public static void main(String[] args) {
            // 代码生成器
            AutoGenerator mpg = new AutoGenerator();

            // 全局配置
            GlobalConfig gc = new GlobalConfig();
            String projectPath = System.getProperty("user.dir");
            //逆向工程生成的文件，要输出到具体的目录地址
            gc.setOutputDir(projectPath + "/src/main/java");
            //作者
            gc.setAuthor("YeYutong");
            //打开输出目录
            gc.setOpen(false);
            //xml开启 BaseResultMap
            gc.setBaseResultMap(true);
            //xml 开启BaseColumnList
            gc.setBaseColumnList(true);
            // 实体属性 Swagger2 注解，因为用到了Swagger2的接口文档
            gc.setSwagger2(false);
            mpg.setGlobalConfig(gc);

            // 数据源配置，和Yml配置内容一样
            DataSourceConfig dsc = new DataSourceConfig();
            dsc.setUrl("jdbc:mysql://101.43.43.19:3307/Library?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia" + "/Shanghai");
            dsc.setDriverName("com.mysql.cj.jdbc.Driver");
            dsc.setUsername("root");
            dsc.setPassword("lsy200324");
            mpg.setDataSource(dsc);

            // 包配置，生成对应类的所在的包名
            PackageConfig pc = new PackageConfig();
            pc.setParent("com.YYT.springbootbookmanager")
                    .setEntity("entity")
                    .setMapper("mappers")
                    .setService("service")
                    .setServiceImpl("service.impl")
                    .setController("controller");
            mpg.setPackageInfo(pc);

            // 自定义配置
            InjectionConfig cfg = new InjectionConfig() {
                @Override
                public void initMap() {
                    // to do nothing
                }
            };

            // 如果模板引擎是 freemarker
            String templatePath = "/templates/mapper.xml.ftl";
            // 如果模板引擎是 velocity
            // String templatePath = "/templates/mapper.xml.vm";

            // 自定义输出配置
            List<FileOutConfig> focList = new ArrayList<>();
            // 自定义配置会被优先输出
            focList.add(new FileOutConfig(templatePath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                    return projectPath + "/yeb-generator/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper"
                            + StringPool.DOT_XML;
                }
            });
            cfg.setFileOutConfigList(focList);
            mpg.setCfg(cfg);

            // 配置模板
            TemplateConfig templateConfig = new TemplateConfig();

            templateConfig.setXml(null);
            mpg.setTemplate(templateConfig);

            // 策略配置
            StrategyConfig strategy = new StrategyConfig();
            //数据库表映射到实体的命名策略。数据库表名有下划线，所以要转为驼峰命名，
            strategy.setNaming(NamingStrategy.underline_to_camel);
            //数据库表字段映射到实体的命名策略。数据库字段本来就是驼峰命名，没有下划线，不需要改变。
            strategy.setColumnNaming(NamingStrategy.no_change);
            //lombok模型
            strategy.setEntityLombokModel(true);
            //生成 @RestController 控制器。 返回前端是json字符串。
            strategy.setRestControllerStyle(true);
            strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
            strategy.setControllerMappingHyphenStyle(true);
            //表前缀。在生成的时候，可以省略掉前缀。
            strategy.setTablePrefix("t_");
            mpg.setStrategy(strategy);
            mpg.setTemplateEngine(new FreemarkerTemplateEngine());
            mpg.execute();
        }}


