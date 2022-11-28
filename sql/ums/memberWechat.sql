-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户微信信息', '3', '1', 'memberWechat', 'ums/memberWechat/index', 1, 0, 'C', '0', '0', 'ums:memberWechat:list', '#', 1, sysdate(), '', null, '用户微信信息菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户微信信息查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'ums:memberWechat:query',        '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户微信信息新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'ums:memberWechat:add',          '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户微信信息修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'ums:memberWechat:edit',         '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户微信信息删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'ums:memberWechat:remove',       '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('用户微信信息导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'ums:memberWechat:export',       '#', 1, sysdate(), '', null, '');
