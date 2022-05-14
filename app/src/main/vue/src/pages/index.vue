<template>
  <div class="index h-vh box-b w-vw b-f" style="max-width: 100%;">
    <van-nav-bar class="b-p nav-bar" title="标题" left-text="返回" left-arrow>
      <van-icon slot="right" name="replay" @click="reloadPage()" />
    </van-nav-bar>
    <h1 class="tip-s b-s1 p4">请求测试</h1>
    <div class="p4 box-b h-vh over-a-y">
      <div v-for="i in 7">
        <el-button class="m2" @click="click(i)"><span class="c-p">{{label[i-1]}}</span></el-button>
        <div v-if="result[i-1]" class="b-p1 tip-p over-a" style="text-overflow: clip;">
          result： {{result[i-1]}}
        </div>
        <div v-if="result[i-1]" class="b-w1 tip-w over-a" style="text-overflow: clip;">
          result['data']：{{result[i-1]['data']}}
        </div>
        <div v-if="result[i-1]" class="b-d1 tip-d">
          result：数据类型{{typeof result[i-1]}}<br />
          result['data']：获取到的数据类型{{typeof result[i-1]['data']}}
        </div>
      </div>
      <div class="w h-200"></div>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        result: [],
        label: ['单字符串', ' 多个参数', '多个字符串返回数组', '多个参数和数组', '实体类', ' 集合,泛型为实体类 List<Article>', '测试自动注入实例化对象'],
        remotes: [{
            path: 'test/get6',
            method: 'get',
            params: '张三'
          }, {
            path: 'test/get',
            method: 'get',
            params: {
              str2: '2',
              str: 'html'
            }
          },
          {
            path: 'test/get2',
            method: 'get',
            params: {
              str2: '2',
              str: 'html'
            }
          },
          {
            path: 'test/get3',
            method: 'get',
            params: {
              str2: '2',
              str: ['html', '张某某']
            }
          },
          {
            path: 'test/get4',
            params: {
              content: '这是文章内容',
              id: '123',
              image: 'imagess',
              isDelete: '0',
              createdBy: '1',
              createdTime: new Date(),
              updatedTime: '2014年02月08日',
              title: '这是标题'
            }
          },

          {
            path: 'test/get5',
            params: [{
              content: '这是文章内容1',
              id: '123',
              image: 'imagess1',
              isDelete: '0',
              createdBy: '1',
              createdTime: new Date(),
              updatedTime: '2014年02月08日',
              title: '这是标题'
            }, {
              content: '这是文章内容2',
              id: '123',
              image: 'imagess2',
              isDelete: '1',
              createdBy: '2',
              createdTime: new Date(),
              updatedTime: '2023年02月08日',
              title: '这是标题3'
            }]
          },

          {
            path: 'test/get7',
          },
        ]
      }
    },
    watch: {},
    methods: {
      click(i) {
        let remote = this.remotes[i - 1];
        let res = this.$ano.requestSync(remote['path'], remote['method'], remote['params']);
        this.$set(this.result, i - 1, res);
      },
      reloadPage() {
        let href = window.location.href;
        window.location.href = "";
        this.$nextTick(() => {
          window.location.href = href;
        });
      }
    },
    created() {

    }
  }
</script>

<style>
  .home {
    height: 100vh;
    overflow: auto;
    min-height: 660px;
  }

  .el-tabs,
  .el-tabs--card,
  .el-tabs--top {
    width: 100%;
  }

  .home .cashier {
    width: 100vw !important;
    height: calc(100vh + 40px) !important;
    overflow: hidden !important;
    z-index: 999 !important;
    top: -40px !important;
    left: 0 !important;
    position: absolute !important;
  }

  .home .cashier .el-tabs__content {
    height: 100%;
  }

  .home .cashier .el-tabs__content [role] {
    height: 100%;

  }

  /* 设置滚动条的样式 */
  ::-webkit-scrollbar {
    width: 8px;
    /*高宽分别对应横竖滚动条的尺寸*/
    height: 8px;
  }

  /* 滚动槽 */
  ::-webkit-scrollbar-track {
    -webkit-box-shadow: inset006pxrgba(0, 0, 0, 0.3);
    border-radius: 10px;
  }

  /* 滚动条滑块 */
  ::-webkit-scrollbar-thumb {
    border-radius: 10px;
    background: rgba(0, 0, 0, 0.1);
    -webkit-box-shadow: inset006pxrgba(0, 0, 0, 0.5);
  }

  ::-webkit-scrollbar-thumb:window-inactive {
    background: rgba(255, 0, 0, 0.4);
  }

  .index .van-nav-bar .van-icon,
  .index .van-nav-bar__text {
    color: #eeeeee !important;
  }

  .index .van-nav-bar__title {
    color: #FFFFFF !important;
  }

  .index .nav-bar {
    position: sticky;
    top: 0;
    z-index: 1;
  }
</style>
