-- 删除 Swagger 菜单及其角色权限关联
DELETE role_menu
FROM sys_role_menu role_menu
INNER JOIN sys_menu menu ON menu.menu_id = role_menu.menu_id
WHERE menu.perms LIKE 'tool:swagger:%'
   OR menu.component = 'tool/swagger/index';

DELETE FROM sys_menu
WHERE perms LIKE 'tool:swagger:%'
   OR component = 'tool/swagger/index';
