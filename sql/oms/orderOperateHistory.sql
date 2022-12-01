-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单操作历史记录', '3', '1', 'orderOperateHistory', 'oms/orderOperateHistory/index', 1, 0, 'C', '0', '0', 'oms:orderOperateHistory:list', '#', 1, sysdate(), '', null, '订单操作历史记录菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单操作历史记录查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'oms:orderOperateHistory:query',        '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单操作历史记录新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'oms:orderOperateHistory:add',          '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单操作历史记录修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'oms:orderOperateHistory:edit',         '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单操作历史记录删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'oms:orderOperateHistory:remove',       '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单操作历史记录导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'oms:orderOperateHistory:export',       '#', 1, sysdate(), '', null, '');
