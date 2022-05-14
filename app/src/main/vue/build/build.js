'use strict'
require('./check-versions')()

process.env.NODE_ENV = 'production'

const ora = require('ora')

const utils = require('./utils')

const rm = require('rimraf')
const path = require('path')
const chalk = require('chalk')
const webpack = require('webpack')
const config = require('../config')
const webpackConfig = require('./webpack.prod.conf')

const spinner = ora('building for production...')

spinner.start()

rm(path.join(config.build.assetsRoot, config.build.assetsSubDirectory), err => {
  if (err) throw err
  webpack(webpackConfig, (err, stats) => {
    spinner.stop()
    if (err) throw err
    process.stdout.write(stats.toString({
      colors: true,
      modules: false,
      children: false, // If you are using ts-loader, setting this to true will make TypeScript errors show up during build.
      chunks: false,
      chunkModules: false
    }) + '\n\n')

    if (stats.hasErrors()) {
      console.log(chalk.red('  Build failed with errors.\n'))
      process.exit(1)
    }


    //写入文件
    setTimeout(() => {
      copyToExeOut()
    }, 2000);


    console.log(chalk.cyan('  Build complete.\n'))
    console.log(chalk.yellow(
      '  Tip: built files are meant to be served over an HTTP server.\n' +
      '  Opening index.html over file:// won\'t work.\n'
    ))


  })
})

//拷贝文件到当前目录下的 bin/x64/Debug/ui 或者 bin/x86/Debug/ui
const copyToExeOut = function() {

  utils.createDir(path.join(__dirname, `../../assets`));

  utils.createDir(path.join(__dirname, `../../assets/ui`));

	//清空原有的文件
   utils.removeDirToDist(path.join(__dirname, `../../assets/ui/`));


  //复制文件到指定目录
  const src = path.join(__dirname, `../../vue/dist/`);
  console.log(chalk.cyan(`文件目录：file\\\:${src}`))

  const dst = path.join(__dirname, `../../assets/ui`);


  utils.copyDirToDist(src, dst);


//  //将打包后的文件访问信息写入到url文件中
//  utils.writeFileToVsConfig('url', `file://${dst}/index.html`);

  console.log(chalk.yellow(`目标目录：file\\\:${dst}`))


  console.log(chalk.cyan(`打包成功，请启动visual studio 进行查看`))

//  //复制logo等信息
//  const src2 = path.join(__dirname, `../../../v-config`);
//  const dst2 = path.join(__dirname, `../../../bin/Release/v-config`);
//  utils.copyDirToDist(src2, dst2);


  return;

}
