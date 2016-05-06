package com.infrastructure.project.base.model.models;

/**
* @ClassName: Entity
* @Description: TODO(抽象基础数据模型
* Java中的instanceof相当于C#的is)
* @author BEE 
* @date 2015-11-19 上午10:13:42
* @param <PKType>
 */
public abstract class Entity<PKType extends Number> {
	
	/**
	 * 主键id
	 */
    protected PKType id;
    /**
     * hibernate事务的版本控制字段
     */
    protected int version;
    
	public PKType getId() {
		return this.id;
	}
	public void setId(PKType id) {
		this.id = id;
	}
	public int getVersion() {
		return this.version;
	}
	public void setVersion(int version) {
		this.version = version;
	}

	
}
