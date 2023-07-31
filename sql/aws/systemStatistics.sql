-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('系统数据统计', '3', '1', 'systemStatistics', 'aws/systemStatistics/index', 1, 0, 'C', '0', '0', 'aws:systemStatistics:list', '#', 1, sysdate(), '', null, '系统数据统计菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('系统数据统计查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'aws:systemStatistics:query',        '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('系统数据统计新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'aws:systemStatistics:add',          '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('系统数据统计修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'aws:systemStatistics:edit',         '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('系统数据统计删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'aws:systemStatistics:remove',       '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('系统数据统计导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'aws:systemStatistics:export',       '#', 1, sysdate(), '', null, '');
