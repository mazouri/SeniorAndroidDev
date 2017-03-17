##GreenDao

## 简介
GreenDao这个开源项目有助于开发者与存储在Sqlite中的数据打交道。Sqlite是关系型数据库，但是，用Sqlite开发需要一些额外的工作，比如我们需要写SQL语句和解析查询的结果，这都是相当繁琐的工作。GreenDao将会替我们做这些额外的工作。它将Java对象映射到数据库的表，这种方式我们一班称之为ORM，也就是object/relational mapping对象映射到数据库。通过这种方式，我们可以使用简单的面向对象的API来存储、更新、删除以及查询Java对象。节省时间，将精力聚焦于真正的问题上面。

##GreenDao的优点
性能高，号称Android最快的关系型数据库;内存占用小;库文件比较小，小于100K，编译时间低，而且可以避免65K方法限制;支持数据库加密;简洁易用的API;

##GreenDao 3.0改动
 使用过GreenDao的同学都知道，3.0之前需要通过新建GreenDaoGenerator工程生成Java数据对象（实体Entity）和DAO对象，非常的繁琐而且也加大了使用成本。
GreenDao  3.0最大的变化就是采用注解的方式通过编译方式生成Java数据对象和DAO对象。
可以通过@Entity注解一个实体类，一个实体类Entity就是对应一张表，默认表名就是类名，让然可以通过注解的nameInDb值设置表名。

greenDAO会自动根据实体类属性创建表字段，并赋予默认值。例如在数据库方面的表名和列名都来源于实体类名和属性名。
可以通过@Id设置主键，@Id(autoincrement = true)设置主键自增长。

