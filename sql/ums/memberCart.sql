-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('购物车', '3', '1', 'memberCart', 'ums/memberCart/index', 1, 0, 'C', '0', '0', 'ums:memberCart:list', '#', 1, sysdate(), '', null, '购物车菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('购物车查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'ums:memberCart:query',        '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('购物车新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'ums:memberCart:add',          '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('购物车修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'ums:memberCart:edit',         '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('购物车删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'ums:memberCart:remove',       '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('购物车导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'ums:memberCart:export',       '#', 1, sysdate(), '', null, '');
