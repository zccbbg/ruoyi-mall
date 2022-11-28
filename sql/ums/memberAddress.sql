-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('会员收货地址', '3', '1', 'memberAddress', 'ums/memberAddress/index', 1, 0, 'C', '0', '0', 'ums:memberAddress:list', '#', 1, sysdate(), '', null, '会员收货地址菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('会员收货地址查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'ums:memberAddress:query',        '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('会员收货地址新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'ums:memberAddress:add',          '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('会员收货地址修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'ums:memberAddress:edit',         '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('会员收货地址删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'ums:memberAddress:remove',       '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('会员收货地址导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'ums:memberAddress:export',       '#', 1, sysdate(), '', null, '');
