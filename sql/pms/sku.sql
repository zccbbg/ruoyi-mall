-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('sku信息', '3', '1', 'sku', 'pms/sku/index', 1, 0, 'C', '0', '0', 'pms:sku:list', '#', 1, sysdate(), '', null, 'sku信息菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('sku信息查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'pms:sku:query',        '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('sku信息新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'pms:sku:add',          '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('sku信息修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'pms:sku:edit',         '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('sku信息删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'pms:sku:remove',       '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('sku信息导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'pms:sku:export',       '#', 1, sysdate(), '', null, '');
