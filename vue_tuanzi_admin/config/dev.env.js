'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',

  //开发环境
  ADMIN_API: '"http://localhost:8607/tuanzi-admin"',
  PICTURE_API: '"http://localhost:8607/tuanzi-picture"',
  PICTURE_INFO: '"http://localhost:8607/tuanzi-picture/picRequest"',
  WEB_API: '"http://localhost:8607/tuanzi-web"',
  Search_API: '"http://localhost:8607/tuanzi-search"',
  Spider_API: '"http://localhost:8607/tuanzi-spider"',
  FILE_API: '"http://localhost:8600/"',
  BLOG_WEB_URL: '"http://localhost:9527"',
  SOLR_API: '"http://localhost:8080/solr"',
  ELASTIC_SEARCH: '"http://localhost:5601"',
})
