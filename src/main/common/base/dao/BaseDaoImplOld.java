package main.common.base.dao;


import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

import main.common.base.excption4dao.Exception4Dao;
import main.common.base.web.page.PageParam;





/** 
 * @ClassName: BaseDaoImpl 
 * @Description: TODO
 * @author qinyex
 * @date 2018年5月21日 上午1:56:58 
 * @version  [1.0, 2018年5月21日]
 * @since  version 1.0
 * @param <T> 
 */
@SuppressWarnings("unchecked")
@Service
public abstract class BaseDaoImplOld<T> implements BaseDao<T> {
	

	@Resource
	private SqlSession sqlSession;
	
	public static final String SQL_QUERYALL = "selectAll";
	public static final String SQL_INSERT = "insert";
	public static final String SQL_LOAD = "selectByPrimaryKey";
	public static final String SQL_DELETE = "deleteByPrimaryKey";
	public static final String SQL_UPDATE = "updateByPrimaryKey";

	 
	private Class<T> clazz;

	public BaseDaoImplOld() {
		// 使用反射技术得到T的真实类型
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass(); // 获取当前new的对象的 泛型的父类 类型
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0]; // 获取第一个类型参数的真实类型
		System.out.println("clazz ---> " + clazz);
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}

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
	/**
	 * 获取当前可用的Session
	 * 
	 * @return
	 */
	 public SqlSession setSqlSession(SqlSession sqlSession) {
		   return  this.sqlSession = sqlSession;
		  }
	 
	 /**
	  * 查询所有 带分页
	  */
	public List<T> queryList(Map<String, Object> paramMap ,PageParam page) {
			PageHelper.startPage(page.getPageNum(),page.getNumPerPage());
		
			List<T> selectList = sqlSession.selectList(getStatement(SQL_QUERYALL),paramMap);
			ValueStack valueStack = ActionContext.getContext().getValueStack();
			//设置分页信息
			valueStack.set("page", new PageInfo<>(selectList));
			return selectList;
			
	}
	
	
	 /**
	  * 查询所有不带分页
	  */
	public List<T> queryList(Map<String, Object> paramMap ) {
			HashMap<Object, Object> param = new HashMap<>();
			List<T> queryList = sqlSession.selectList(getStatement(SQL_QUERYALL),paramMap);
			
			return queryList;
			
	}
	
	/**
	 * 添加记录.
	 * 
	 * @param entity
	 *            .
	 * @return id . 
	 */
	public long insert(T entity) {
		
		int result = sqlSession.insert(getStatement(SQL_INSERT), entity);
		if (result <= 0) {
			throw Exception4Dao.DB_INSERT_RESULT_0.newInstance("数据库操作,update返回0.{%s}", getStatement(SQL_INSERT));
		}
		return result;
	}
	
	/**
	 *根据id查询记录
	 * 
	 * @param entity
	 */
	public T load(String  id) {
		 T selectOne = sqlSession.selectOne(getStatement(SQL_LOAD), id);
		 return  selectOne;
	}
	/**
	 * 根据id删除
	 */
	public void delete(String id) {
		int delete = sqlSession.delete(getStatement(SQL_DELETE), id);
		System.out.println("删除"+delete+"条数据");
	}
	/**
	 * 更新记录
	 */
	public void update(T entity) {
		int result = sqlSession.update(getStatement(SQL_UPDATE), entity);
		if (result <= 0) {
			throw Exception4Dao.DB_UPDATE_RESULT_0.newInstance("数据库操作,update返回0.{%s}", getStatement(SQL_UPDATE));
		}
		
	}
	/**
	 * 
	 */
	public T autoSql(String  sqqlId, Object obj) {
		
		String statement = getStatement(sqqlId);
		 T selectOne = sqlSession.selectOne(statement, obj);
		
		return selectOne;
		
	}
	
	


}
