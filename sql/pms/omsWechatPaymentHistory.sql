-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('微信订单表', '3', '1', 'wechatPaymentHistory', 'pms/wechatPaymentHistory/index', 1, 0, 'C', '0', '0', 'pms:wechatPaymentHistory:list', '#', 1, sysdate(), '', null, '微信订单表菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('微信订单表查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'pms:wechatPaymentHistory:query',        '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('微信订单表新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'pms:wechatPaymentHistory:add',          '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('微信订单表修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'pms:wechatPaymentHistory:edit',         '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('微信订单表删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'pms:wechatPaymentHistory:remove',       '#', 1, sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('微信订单表导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'pms:wechatPaymentHistory:export',       '#', 1, sysdate(), '', null, '');
