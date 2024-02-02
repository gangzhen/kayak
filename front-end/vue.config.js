const {defineConfig} = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true,
    devServer: {
        port: 5000
    },
    chainWebpack: config => {
        config.plugin('html')
            .tap(args => {
                args[0].title = '赛艇皮划艇赛事管理系统';
                return args;
            })
    }
})
