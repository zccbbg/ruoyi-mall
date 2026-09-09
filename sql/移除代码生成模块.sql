-- 删除代码生成模块菜单及其角色权限关联
DELETE role_menu
FROM sys_role_menu role_menu
INNER JOIN sys_menu menu ON menu.menu_id = role_menu.menu_id
WHERE menu.perms LIKE 'tool:gen:%'
   OR menu.component = 'tool/gen/index';

DELETE FROM sys_menu
WHERE perms LIKE 'tool:gen:%'
   OR component = 'tool/gen/index';

-- 删除代码生成模块的业务表
DROP TABLE IF EXISTS gen_table_column;
DROP TABLE IF EXISTS gen_table;
