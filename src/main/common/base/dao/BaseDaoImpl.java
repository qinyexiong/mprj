package main.common.base.dao;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

import main.common.base.entity.BaseEntityDao;
import main.common.base.excption4dao.Exception4Dao;
import main.common.base.web.page.PageParam;

/**
 * @ClassName: BaseDaoImpl
 * @Description:
 * @author qinyex
 * @date 2018年5月21日 上午1:56:58
 * @version [1.0, 2018年5月21日]
 * @since version 1.0
 * @param <T>
 *
 */

@Service
public abstract class BaseDaoImpl<T extends BaseEntityDao> extends SqlSessionDaoSupport implements BaseDao<T> {

	public static final String SQL_QUERYALL = "selectAll";
	public static final String SQL_INSERT = "insert";
	public static final String SQL_LOAD = "selectByPrimaryKey";
	public static final String SQL_DELETE = "deleteByPrimaryKey";
	public static final String SQL_UPDATE = "updateByPrimaryKey";
	private Class<T> clazz;

	public BaseDaoImpl() {
		// 使用反射技术得到T的真实类型
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass(); // 获取当前new的对象的 泛型的父类 类型
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0]; // 获取第一个类型参数的真实类型
		System.out.println("clazz ---> " + clazz);
	}

	// 这样的方法也可以,最开始就用的这个 可以不用 extends SqlSessionDaoSupport
	// @Resource
	// private SqlSession sqlSession;

	/**
	 * 注入SqlSessionTemplate实例(要求Spring中进行SqlSessionTemplate的配置).<br/>
	 * 可以调用sessionTemplate完成数据库操作.
	 */
	@Autowired
	private SqlSessionTemplate sessionTemplate;

	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}

	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}

	public SqlSession getSqlSession() {
		return super.getSqlSession();
	}

	/**
	 * 错误 :Property 'sqlSessionFactory' or 'sqlSessionTemplate' are required
	 * 从SqlSessionDaoSupport
	 * 这个类的源码中可以看出，原因是mybatis-spring-1.2.0中取消了自动注入SqlSessionFactory 和
	 * SqlSessionTemplate
	 *
	 *
	 * 可能是为了解决多数据源的问题吧，取消了自动注入。没用到多数据源，不太关心这个。
	 * 解决方案：因为我们dao层是继承于一个dao基类，所以只要在这个基类中注入任意一个属性即可。SqlSessionFactory在spring配置文件中已经配置。
	 */
	@Resource
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	// *****************TODO 数据库的操作 **************************

	/**
	 * 添加记录.
	 * 
	 * @param entity
	 *            .
	 * @return id .
	 */
	public long insert(T entity) {

		int result = sessionTemplate.insert(getStatement(SQL_INSERT), entity);
		if (result <= 0) {
			throw Exception4Dao.DB_INSERT_RESULT_0.newInstance("数据库操作,update返回0.{%s}", getStatement(SQL_INSERT));
		}
		return result;
	}

	/**
	 * 根据id查询记录
	 * 
	 * @param entity
	 */
	public T load(String id) {
		T selectOne = sessionTemplate.selectOne(getStatement(SQL_LOAD), id);
		return selectOne;
	}

	/**
	 * 根据id删除
	 */
	public void delete(String id) {
		int delete = sessionTemplate.delete(getStatement(SQL_DELETE), id);
		System.out.println("删除" + delete + "条数据");
	}

	/**
	 * 更新记录
	 */
	public void update(T entity) {
		int result = sessionTemplate.update(getStatement(SQL_UPDATE), entity);
		if (result <= 0) {
			throw Exception4Dao.DB_UPDATE_RESULT_0.newInstance("数据库操作,update返回0.{%s}", getStatement(SQL_UPDATE));
		}

	}

	/**
	 * 
	 */
	public T autoSql(String sqqlId, Object obj) {

		String statement = getStatement(sqqlId);
		T selectOne = sessionTemplate.selectOne(statement, obj);

		return selectOne;

	}

	/**
	 * 查询所有 带分页
	 */
	public List<T> queryList(Map<String, Object> paramMap, PageParam page) {
		PageHelper.startPage(page.getPageNum(), page.getNumPerPage());

		List<T> selectList = sessionTemplate.selectList(getStatement(SQL_QUERYALL), paramMap);
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		// 设置分页信息
		valueStack.set("page", new PageInfo<>(selectList));
		return selectList;

	}

	/**
	 * 查询所有不带分页
	 */
	public List<T> queryList(Map<String, Object> paramMap) {
		HashMap<Object, Object> param = new HashMap<>();
		List<T> queryList = sessionTemplate.selectList(getStatement(SQL_QUERYALL), paramMap);

		return queryList;

	}

	/****** TODO 获取Mapper命名空间. ****/
	/**
	 * 获取Mapper命名空间.
	 * 
	 * @param sqlId
	 * @return
	 */
	public String getStatement(String sqlId) {
		String name = clazz.getName();
		StringBuffer sb = new StringBuffer();
		sb.append(name).append(".").append(sqlId);
		String statement = sb.toString();

		return statement;
	}

}
