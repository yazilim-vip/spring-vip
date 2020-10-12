
import React from 'react';
import ComponentCreator from '@docusaurus/ComponentCreator';
export default [
{
  path: '/',
  component: ComponentCreator('/','deb'),
  exact: true,
},
{
  path: '/blog',
  component: ComponentCreator('/blog','119'),
  exact: true,
},
{
  path: '/blog/2019/05/28/hola',
  component: ComponentCreator('/blog/2019/05/28/hola','861'),
  exact: true,
},
{
  path: '/versions',
  component: ComponentCreator('/versions','b27'),
  exact: true,
},
{
  path: '/docs/next',
  component: ComponentCreator('/docs/next','c91'),
  
  routes: [
{
  path: '/docs/next/',
  component: ComponentCreator('/docs/next/','813'),
  exact: true,
},
{
  path: '/docs/next/exGenericCrudService1',
  component: ComponentCreator('/docs/next/exGenericCrudService1','d78'),
  exact: true,
},
{
  path: '/docs/next/exGenericCrudServiceRestController1',
  component: ComponentCreator('/docs/next/exGenericCrudServiceRestController1','86a'),
  exact: true,
},
{
  path: '/docs/next/styleGuide',
  component: ComponentCreator('/docs/next/styleGuide','459'),
  exact: true,
},
]
},
{
  path: '/docs',
  component: ComponentCreator('/docs','cf6'),
  
  routes: [
{
  path: '/docs/',
  component: ComponentCreator('/docs/','0b5'),
  exact: true,
},
{
  path: '/docs/exGenericCrudService1',
  component: ComponentCreator('/docs/exGenericCrudService1','8dc'),
  exact: true,
},
{
  path: '/docs/exGenericCrudServiceRestController1',
  component: ComponentCreator('/docs/exGenericCrudServiceRestController1','29f'),
  exact: true,
},
{
  path: '/docs/styleGuide',
  component: ComponentCreator('/docs/styleGuide','b58'),
  exact: true,
},
]
},
{
  path: '*',
  component: ComponentCreator('*')
}
];
