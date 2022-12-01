-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单中所包含的商品', '3', '1', 'orderItem', 'oms/orderItem/index', 1, 0, 'C', '0', '0', 'oms:orderItem:list', '#', 1, sysdate(), '', null, '订单中所包含的商品菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单中所包含的商品查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'oms:orderItem:query',        '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单中所包含的商品新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'oms:orderItem:add',          '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单中所包含的商品修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'oms:orderItem:edit',         '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单中所包含的商品删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'oms:orderItem:remove',       '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单中所包含的商品导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'oms:orderItem:export',       '#', 1, sysdate(), '', null, '');
