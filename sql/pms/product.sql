-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品信息', '3', '1', 'product', 'pms/product/index', 1, 0, 'C', '0', '0', 'pms:product:list', '#', 1, sysdate(), '', null, '商品信息菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品信息查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'pms:product:query',        '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品信息新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'pms:product:add',          '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品信息修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'pms:product:edit',         '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品信息删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'pms:product:remove',       '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品信息导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'pms:product:export',       '#', 1, sysdate(), '', null, '');
