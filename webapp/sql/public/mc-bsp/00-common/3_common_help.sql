-- 通讯录通用帮助初始化sql
INSERT INTO help_urls (HELP_CODE, HELP_NAME, URL, MODULE_CODE, MODULE_NAME, DESCRIPTION) VALUES ('bsp_contact', '通讯录通用帮助', 'jsp/gcloud/contact/contact_common_help.jsp', 'BSP_CONTACT', 'BSP_CONTACT', NULL);

INSERT INTO help_modules (MODULE_CODE, MODULE_NAME, ISLOCAL, TRANSPORT, HOST, PORT, WEBCONTENT, URI, NOTE) VALUES ('BSP_CONTACT', '通讯录', '0', 'http', '10.12.1.80', '80', 'bsp', 'http://10.12.1.80:80/bsp/', '通讯录通用帮助');
