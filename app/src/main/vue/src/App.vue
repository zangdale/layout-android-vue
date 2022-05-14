<template>
  <div class="w h">
    <router-view />
  </div>
</template>

<script>
  export default {
    name: 'App',
    data() {
      return {
        transitionName: 'vux-pop-in', //初始过渡动画方向
        showTitleBar: false,
      }
    },
    watch: {
      $route(to, from) {
        var isBack = this.$router.isBack;
        if (isBack) {
          this.transitionName = 'vux-pop-in';
        } else {
          this.transitionName = 'vux-pop-out';
        }
        this.$router.isBack = false;
      }
    },
    methods: {
      init() {
        //拦截器
        this.$ano.requestSetting.interceptor = {
          /**
           * 请求之前调用的方法
           * @param {object} 请求之前的所构造参数
           * @return Boolean  true,false 表示是否继续执行
           */
          before: (event) => {
            return true;
          },
          /**
           * 请求之后调用的方法,对返回数据进行加工操作
           * @param {object} 请求之前的所构造参数
           * @return Object event 表示加工后返回的数据
           */
          after: (event) => {
            return event;
          },
          /**
           * 异常处理程序
           */
          error: (error, option) => {
            return error;
          }
        }
      }
    },
    created() {
      this.init();
    }
  }
</script>

<style>

  /* 设置滚动条的样式 */
  ::-webkit-scrollbar {
    width: 8px;
    /*高宽分别对应横竖滚动条的尺寸*/
    height: 8px;
  }

  /* 滚动槽 */
  ::-webkit-scrollbar-track {
    -webkit-box-shadow: inset 2px rgba(0, 0, 0, 0.3);
    border-radius: 2px;
  }

  /* 滚动条滑块 */
  ::-webkit-scrollbar-thumb {
    border-radius: 2px;
    background: rgba(0, 0, 0, 0.1);
    -webkit-box-shadow: inset  2px rgba(0, 0, 0, 0.5);
  }

  ::-webkit-scrollbar-thumb:window-inactive {
    background: rgba(255, 0, 0, 0.4);
  }
</style>
