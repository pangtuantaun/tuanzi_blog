<template>
  <div id="table" class="app-container calendar-list-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container" style="margin: 10px 0 10px 0;">
      <el-button v-permission="'/picture/add'" class="filter-item" type="primary" icon="el-icon-edit"
                 @click="handleAdd">添加
      </el-button>
      <!--      <el-button class="filter-item" type="primary" @click="handleReturn" icon="el-icon-s-promotion">返回分类</el-button>-->
      <el-button class="button" type="primary" icon="el-icon-refresh" @click="checkAll()">{{ chooseTitle }}</el-button>
      <el-button v-permission="'/picture/delete'" class="filter-item" type="danger" icon="el-icon-delete"
                 @click="handleDeleteBatch">删除选中
      </el-button>
      <el-button v-permission="'/picture/setCover'" class="filter-item" type="success" icon="el-icon-video-camera-solid"
                 @click="setCover">设为封面
      </el-button>
    </div>

    <el-tabs
      v-model="activeName"
      type="border-card"
      tab-position="left"
      style="height: 800px; width: 100%;"
      @tab-click="clickTab"
    >
      <el-tab-pane
        v-for="(pictureSort, index) in pictureSorts"
        :key="index"
        style="height: 800px; width: 100%; overflow:auto;"
      >
        <div slot="label" class="sortItem" style="float:left">
          <i class="el-icon-picture"/>
          {{ pictureSort.name }}
        </div>

        <el-row>
          <el-col
            v-for="(picture, index) in pictureSort.pictures"
            :key="picture.uid"
            :xs="24"
            :sm="12"
            :md="12"
            :lg="6"
            :xl="4"
            style="padding: 6px"
          >
            <el-card
              :body-style="{ padding: '0px', textAlign: 'center' }"
              shadow="always"
            >
              <input :id="picture.uid" :checked="pictureUids.indexOf(picture.uid)>=0"
                     style="position: absolute;z-index: 100;" type="checkbox" @click="checked(picture)">
              <el-image
                :src="picture.pictureUrl"
                style="cursor:pointer"
                fit="scale-down"
                @click="showPicture(picture.pictureUrl)"
              />
              <div @click="showPicture(picture.pictureUrl)">
                <span v-if="picture.picName" class="media-title">{{ picture.picName }}</span>
                <span v-else class="media-title">图片 {{ index + 1 }}</span>
              </div>
              <div style="margin-bottom: 14px;">
                <el-button-group>
                  <el-tooltip class="item" effect="dark" content="复制图片地址" placement="bottom-start"
                              style="margin-right: 2px">
                    <el-button
                      size="mini"
                      icon="el-icon-copy-document"
                      @click="copyUrl(picture.pictureUrl)"
                    />
                  </el-tooltip>

                  <el-tooltip class="item" effect="dark" content="复制Markdown格式图片地址" placement="bottom-start"
                              style="margin-right: 2px">
                    <el-button
                      type="primary"
                      size="mini"
                      icon="el-icon-document-copy"
                      @click="copyMarkdownUrl(picture.pictureUrl, picture.pictureUrl)"
                    />
                  </el-tooltip>

                  <el-tooltip v-permission="'/picture/add'" class="item" effect="dark" content="裁剪图片"
                              placement="bottom-start" style="margin-right: 2px">
                    <el-button
                      type="warning"
                      size="mini"
                      icon="el-icon-s-open"
                      @click="handleCropper(picture)"
                    />
                  </el-tooltip>

                  <el-tooltip v-permission="'/picture/delete'" class="item" effect="dark" content="删除图片"
                              placement="bottom-start" style="margin-right: 2px">
                    <el-button
                      type="danger"
                      size="mini"
                      icon="el-icon-delete"
                      @click="handleDelete(picture)"
                    />
                  </el-tooltip>

                </el-button-group>
              </div>
            </el-card>

          </el-col>
        </el-row>

        <!--分页-->
        <div class="block">
          <el-pagination
            :current-page.sync="currentPage"
            :page-size="pageSize"
            :total="total"
            layout="total, prev, pager, next, jumper"
            @current-change="handleCurrentChange"/>
        </div>
      </el-tab-pane>

    </el-tabs>

    <!-- 添加或修改对话框 -->
    <el-dialog :title="title" :visible.sync="dialogFormVisible">

      <el-upload
        ref="upload"
        :on-success="fileSuccess"
        :action="uploadPictureHost"
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        :data="otherData"
        class="upload-demo"
        drag
        name="filedatas"
        multiple>
        <i class="el-icon-upload"/>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div slot="tip" class="el-upload__tip">只能上传图片，且不超过5MB</div>
      </el-upload>

    </el-dialog>

    <el-dialog :visible.sync="dialogPictureVisible" fullscreen style="text-align: center">
      <img :src="dialogImageUrl" alt="">
    </el-dialog>

    <el-dialog :visible.sync="pictureCropperVisible" fullscreen>
      <PictureCropper v-if="reFresh" :model-src="checkedPicture.pictureUrl" :file-name="checkedPicture.picName"
                      @cropperSuccess="cropperSuccess"/>
    </el-dialog>

  </div>
</template>

<script>
import {
  getPictureList,
  addPicture,
  editPicture,
  deletePicture,
  setCover
} from '@/api/picture'
import { getPictureSortList, getPictureSortByUid } from '@/api/pictureSort'
import { getToken } from '@/utils/auth'
import PictureCropper from '@/components/PictureCropper'
import Vue from 'vue'

export default {
  components: {
    PictureCropper
  },
  data() {
    return {
      dialogImageUrl: '', // 图片显示地址
      checkedPicture: {}, // 单选的图片
      pictureCropperVisible: false, // 裁剪图片框是否显示
      dialogPictureVisible: false,
      uploadPictureHost: null,
      fileList: [],
      pictureSortUid: undefined, // 当前选中的图片分类uid
      pictureSort: {}, // 当前选中的图片分类
      pictureUids: [], // 图片uid集合
      pictureUploadList: [], // 图片上传列表
      chooseTitle: '全选',
      isCheckedAll: false, // 是否全选
      fileUids: '', // 上传时候的文件uid
      form: {
        uid: null,
        fileUid: null,
        picName: null,
        pictureSortUid: null
      },
      tableData: [], // 显示的图片列表
      count: 0, // 计数器，用于记录上传次数
      loading: true,
      currentPage: 1,
      pageSize: 18,
      total: null,
      title: '增加图片',
      dialogFormVisible: false,
      keyword: '',
      reFresh: true, // 是否刷新组件
      activeName: '0',
      pictureSorts: [],
      havePictureSorts: false // 是否加载完图片分类
    }
  },
  watch: {
    checkedPicture(val) {
      this.reFresh = false
    }
  },
  created() {
    // 传递过来的pictureSordUid
    this.pictureSortUid = this.$route.query.pictureSortUid
    // 图片上传地址
    this.uploadPictureHost = process.env.PICTURE_API + '/file/pictures'
    // 其它数据
    this.otherData = {
      source: 'picture',
      userUid: 'uid00000000000000000000000000000000',
      adminUid: 'uid00000000000000000000000000000000',
      projectName: 'blog',
      sortName: 'admin',
      token: getToken()
    }
    this.initPictureSortList()
  },
  methods: {
    initPictureSortList: function() {
      // 先加载分类
      var that = this
      if (!this.havePictureSorts) {
        var params = {}
        // TODO 全部把分类加载出来，如果图片很多的话，不能这么做
        params.pageSize = 500
        params.currentPage = 1
        getPictureSortList(params).then(function(response) {
          if (response.code == that.$ECode.SUCCESS) {
            var pictureSorts = response.data.records
            that.pictureSorts = pictureSorts
            if (pictureSorts.length > 0) {
              // 判断是否通过图片分类跳转的
              var pictureSortUid = that.pictureSortUid
              // 默认初始化第一个【给第一个tab添加初始化信息】
              if (pictureSortUid == undefined) {
                // 当没有被定义的时候，默认加载第一个
                that.pictureSortUid = pictureSorts[0].uid
                pictureSortUid = pictureSorts[0].uid
                // 设置默认选中的图片分类
                that.pictureSort = pictureSorts[0]
              } else {
                // 如果不为空，说明是通过图片分类跳转的，找出当前id所在的角标
                for (let i = 0; i < pictureSorts.length; i++) {
                  if (pictureSorts[i].uid == pictureSortUid) {
                    // 设置选中的分类和激活的index
                    that.activeName = '' + i
                    that.pictureSort = pictureSorts[i]
                  }
                }
              }
              that.clickTab(null)
            }
          } else {
            that.$message({ type: 'error', message: response.data })
          }
        })
      }
    },
    clickTab(e) {
      var that = this
      var index = this.activeName
      var pictureSortUid = this.pictureSorts[index].uid == undefined ? this.pictureSorts[index].pictureSortUid : this.pictureSorts[index].uid
      // 当前pictureSortUid
      this.pictureSortUid = pictureSortUid
      this.pictureSort = this.pictureSorts[index]
      var name = this.pictureSorts[index].name
      var params = {}
      params.currentPage = 1
      params.pictureSortUid = pictureSortUid
      params.pageSize = that.pageSize
      getPictureList(params).then(function(response) {
        if (response.code == that.$ECode.SUCCESS) {
          if (response.data.records.length > 0) {
            response.data.records.forEach(function(value) {
                value.pictureUrl = process.env.PICTURE_INFO + value.pictureUrl
              }
            )
            var newObject = {
              pictureSortUid: pictureSortUid,
              name: name,
              pictures: response.data.records,
              pageSize: response.data.size,
              currentPage: response.data.current,
              total: response.data.total
            }
            that.pageSize = response.data.size
            that.currentPage = response.data.current
            that.total = response.data.total
            Vue.set(that.pictureSorts, index, newObject)
            that.tableData = response.data.records
          }
        } else {
          this.$commonUtil.message.error(response.message)
        }
      })
    },
    handleCurrentChange: function(val) {
      var that = this
      var pictureSort = this.pictureSort
      var params = {}
      params.pictureSortUid = this.pictureSortUid
      params.currentPage = val
      params.pageSize = that.pageSize
      getPictureList(params).then(function(response) {
        if (response.code == that.$ECode.SUCCESS) {
          response.data.records.forEach(function(value) {
              value.pictureUrl = process.env.PICTURE_INFO + value.pictureUrl
            }
          )
          var newObject = {
            pictureSortUid: pictureSort.uid,
            name: pictureSort.name,
            pictures: response.data.records,
            pageSize: response.data.size,
            currentPage: response.data.current,
            total: response.data.total
          }
          that.pageSize = response.data.size
          that.currentPage = response.data.current
          that.total = response.data.total
          that.tableData = response.data.records
          Vue.set(that.pictureSorts, that.activeName, newObject)
        } else {
          this.$commonUtil.message.error(response.message)
        }
      })
    },
    getFormObject: function() {
      var formObject = {
        uid: null,
        fileUid: null,
        picName: null,
        pictureSortUid: null
      }
      return formObject
    },
    showPicture: function(url) {
      this.dialogPictureVisible = true
      this.dialogImageUrl = url
    },
    copyUrl(url) {
      this.$commonUtil.copyText(url)
      this.$commonUtil.message.success('复制链接到剪切板成功')
    },
    copyMarkdownUrl(name, url) {
      const text = '![' + name + '](' + url + ')'
      this.$commonUtil.copyText(text)
      this.$commonUtil.message.success('复制Markdown格式链接到剪切板成功')
    },
    // 点击单选
    checked: function(data) {
      const idIndex = this.pictureUids.indexOf(data.uid)
      if (idIndex >= 0) {
        // 选过了
        this.pictureUids.splice(idIndex, 1)
      } else {
        this.pictureUids.push(data.uid)
      }
      console.log('选择列表', this.pictureUids)
    },
    checkAll: function() {
      // 如果是全选
      if (this.isCheckedAll) {
        this.pictureUids = []
        this.isCheckedAll = false
        this.chooseTitle = '全选'
      } else {
        this.pictureUids = []
        console.log('tableData', this.tableData)
        this.tableData.forEach(function(picture) {
          this.pictureUids.push(picture.uid)
        }, this)
        this.isCheckedAll = true
        this.chooseTitle = '取消全选'
      }
    },
    handleDelete: function(picture) {
      this.pictureUids = [picture.uid]
      this.handleDeleteBatch()
    },
    handleDeleteBatch: function() {
      if (this.pictureUids.length <= 0) {
        this.$commonUtil.message.error('请先选中图片！')
        return
      }
      this.$confirm('是否删除选中图片？, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          const params = {}
          params.uid = this.pictureUids.join(',') // 将数组变成,组成
          deletePicture(params).then(response => {
            if (response.code == this.$ECode.SUCCESS) {
              this.$commonUtil.message.success(response.message)
              // 清空选中的列表
              this.pictureUids = []
              this.checkedPicture = []
              this.handleCurrentChange(this.currentPage)
              this.chooseTitle = '全选'
              this.isCheckedAll = false
            }
          })
        })
        .catch(() => {
          this.$commonUtil.message.info('已取消删除')
        })
    },
    setCover: function() {
      if (this.pictureUids.length != 1) {
        this.$commonUtil.message.error('选择一张图片设为封面图')
        return
      }

      this.$confirm('是否将该图片设为封面？, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          const params = {}
          params.uid = this.pictureUids[0]
          params.pictureSortUid = this.pictureSortUid
          setCover(params).then(response => {
            this.$commonUtil.message.success(response.message)
            this.pictureUids = []
          })
        })
        .catch(() => {
          this.$commonUtil.message.info('已取消')
        })
    },
    handleCropper: function(picture) {
      this.checkedPicture = picture
      setTimeout(() => {
        this.pictureCropperVisible = true
        this.reFresh = true
      }, 10)
    },
    // 裁剪成功后的回调
    cropperSuccess: function(picture) {
      this.pictureCropperVisible = false
      var checkedPicture = this.checkedPicture
      checkedPicture.fileUid = picture.uid
      const params = {}
      params.uid = checkedPicture.uid
      params.fileUid = checkedPicture.fileUid
      params.picName = checkedPicture.picName
      params.pictureSortUid = checkedPicture.pictureSortUid
      editPicture(params).then(response => {
        if (response.code == this.$ECode.SUCCESS) {
          this.$commonUtil.message.success(response.message)
          this.handleCurrentChange(this.currentPage)
        } else {
          this.$commonUtil.message.error(response.message)
        }
      })

      // 清空选中的列表
      this.pictureUids = []
      this.checkedPicture = []
    },
    handleReturn: function() {
      this.$router.push({
        path: 'pictureSort',
        query: {}
      })
    },
    handleAdd: function() {
      this.dialogFormVisible = true
    },
    handlePreview: function() {

    },
    handleRemove: function() {

    },
    submitNormalUpload: function() {
      console.log()
      this.$refs.upload.submit()
    },
    //图片上传成功
    fileSuccess: function(response, file, fileList) {
      var that = this
      if (response.code == this.$ECode.SUCCESS) {
        const file = response.data

        for (let index = 0; index < file.length; index++) {
          const picture = {}
          picture.fileUid = file[index].uid
          picture.pictureSortUid = this.pictureSortUid
          picture.picName = file[index].picName
          this.pictureUploadList.push(picture)
        }

        this.count = this.count + 1
        if (this.count % fileList.length == 0) {
          addPicture(this.pictureUploadList).then(res => {
            if (res.code == this.$ECode.SUCCESS) {
              this.$commonUtil.message.success(res.message)
              this.handleCurrentChange(this.currentPage)
            } else {
              this.$commonUtil.message.error(res.message)
            }
            this.$refs.upload.clearFiles()
            this.fileUids = ''
            this.pictureUploadList = []
          })
        }
      } else {
        this.$commonUtil.message.error(response.message)
      }
    }
  }
}
</script>

<style scoped>
.media-title {
  color: #8492a6;
  font-size: 14px;
  padding: 3px;
  display: inline-block;
  white-space: nowrap;
  width: 60%;
  overflow: hidden;
  text-overflow: ellipsis;
}

.el-image {
  width: 100%;
  height: 160px;
}
</style>
