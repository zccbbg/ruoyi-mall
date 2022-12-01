-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单售后', '3', '1', 'refundItem', 'oms/refundItem/index', 1, 0, 'C', '0', '0', 'oms:refundItem:list', '#', 1, sysdate(), '', null, '订单售后菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单售后查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'oms:refundItem:query',        '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单售后新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'oms:refundItem:add',          '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单售后修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'oms:refundItem:edit',         '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单售后删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'oms:refundItem:remove',       '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单售后导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'oms:refundItem:export',       '#', 1, sysdate(), '', null, '');
