## 练习需求

0. 获取权限列表
1. 获取一条数据
1. 权限添加
2. 权限删除
3. 权限更新
4. 生成 Tree 

5. 手动表关联 
6. 练习投影
7. setReadOnly

8. 条件查询基础
9. @CreatedB

```java
Specification<T> specification = new Specification<T>() {

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> list = new ArrayList<Predicate>();

        if (StringUtils.isNotBlank(searchName)) {
            list.add(cb.like(root.get("name").as(String.class), "%" + searchName + "%"));
        }

        if (StringUtils.isNotBlank(searchId)) {
            list.add(cb.equal(root.get("id").as(Long.class), searchId));
        }

        if (StringUtils.isNotBlank(searchMobile)) {
            list.add(cb.like(root.get("mobile").as(String.class), "%" + searchMobile + "%"));
        }

        Predicate[] p = new Predicate[list.size()];
        return cb.and(list.toArray(p));
    };
};
```
