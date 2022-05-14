<template>
  <div class="title-bar w h-40 f-b box-b" :style="styles" style="z-index: 2021;">
    <div class="title-bar-drag a-i-c f-c p4 a-i-c m-t2" style="flex-grow: 2;user-select: none;">
      {{title}}
    </div>
    <div class="w-100 f-e p4 a-i-c">

      <div class="m-l4">
        <div @click="formState('minimized')" form-type="minimize" title="最小化" class="c-h-f item">
          <i class="el-icon-minus"></i>
        </div>
      </div>

      <div class="m-l4">
        <div @click="showMax=!showMax,formState(showMax?'normal':'maximized')" form-type class="c-h-f item" :title="showMax?'最大化':'还原'">
          <minimize-2-icon v-if="!showMax" size="1.0x" class="icon"></minimize-2-icon>
          <maximize-2-icon v-else size="1.0x" class="icon"></maximize-2-icon>
        </div>
      </div>

      <div class="m-l4">
        <div @click="closeForm()" class=" c-h-f b-h-d item">
          <i class="fa el-icon-close"></i>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import {
    Minimize2Icon,
    Maximize2Icon
  } from 'vue-feather-icons'
  export default {
    props:{
      type:{
        type:String,
        default:'card', //none card line
      }
    },
    components: {
      Minimize2Icon,
      Maximize2Icon
    },
    watch:{
      type(news){
        this.styles=this.getStyle();
      }
    },
    data() {
      return {
        title: 'CefSharp-vue',
        showMax: true,
        styles:{},
      }
    },
    methods: {
      closeForm() {
        this.$confirm('确定退出吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'info'
        }).then(() => {
          window.extend.close();
        }).catch(() => {});
      },
      formState(type) {
        this.$api.windowHander(type);
      },
      getStyle(){
        var _type=this.type||'none';
        const style= {
          'none':{},
          'line':{'border-bottom':'1px solid #ccc'},
          'card':{'box-shadow':'0px 2px 2px 0 rgba(0, 0, 0, .1)'}
        }
        return style[_type];
      }
    },
    created() {
      this.$api.windowHander("getWindowState").then(res => {
        this.showMax = res.toLowerCase() != "maximized";
      })
      this.styles=this.getStyle();
    }
  }
</script>


<style>
  .title-bar ,.title-bar-drag{
    -webkit-app-region: drag;
  }

  .title-bar .item {
    height: 30px;
    width: 30px;
    display: flex;
    box-sizing: border-box;
    justify-content: center;
    align-items: center;
    -webkit-app-region: no-drag;
  }

  .title-bar .item .icon {
    color: rgba(0, 0, 0, 0.4);
  }

  .title-bar [form-type]:hover {
    background-color: rgba(0, 0, 0, 0.2);
  }
	
	.dom-drag{
		 -webkit-app-region: drag;
	}
	
	.dom-no-drag{
    -webkit-app-region: no-drag;
	}
</style>
