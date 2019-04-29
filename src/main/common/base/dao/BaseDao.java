package main.common.base.dao;

import java.util.List;
import java.util.Map;

import main.common.base.web.page.PageParam;
/**
 * 
 * @ClassName: BaseDao 
 * @Description: TODO 基础数据dao接口
 * @author qinyx
 * @date 2018年12月3日 下午2:17:19 
 * @version  [1.0, 2018年12月3日]
 * @since  version 1.0
 * @email qinyexiong@foxmail.com
 * @param <T>
 */

public interface BaseDao<T> {


	/**
	 * 
	 * @Title: queryList 查询所有带分页
	 * @param paramMap
	 * @param page
	 * @return
	 * @return List<T>    返回类型
	 */

	List<T> queryList(Map<String, Object> paramMap ,PageParam page);

	/**
	 * 
	 * @Title: queryList  查询所有不带分页
	 * @param paramMap
	 * @return
	 * @return List<T>    返回类型
	 */
	public List<T> queryList(Map<String, Object> paramMap );

	/**
	 *根据id查询记录
	 * 
	 * @param entity
	 */
	T load(String  id);
	
	/**
	 * 根据实体对象新增记录.
	 * 
	 * @param entity
	 *        
	 * @return id .
	 */
	long insert(T entity);

	/**
	 * 删除实体
	 * 
	 * @param id
	 */
	void delete(String id);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	void update(T entity);



	
	
	
	
}
