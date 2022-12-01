-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单发货记录', '3', '1', 'orderDeliveryHistory', 'oms/orderDeliveryHistory/index', 1, 0, 'C', '0', '0', 'oms:orderDeliveryHistory:list', '#', 1, sysdate(), '', null, '订单发货记录菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单发货记录查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'oms:orderDeliveryHistory:query',        '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单发货记录新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'oms:orderDeliveryHistory:add',          '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单发货记录修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'oms:orderDeliveryHistory:edit',         '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单发货记录删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'oms:orderDeliveryHistory:remove',       '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单发货记录导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'oms:orderDeliveryHistory:export',       '#', 1, sysdate(), '', null, '');
