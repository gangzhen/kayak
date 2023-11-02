<script>
import ValidCodeDisplay from "@/components/ValidCodeDisplay.vue";

export default {
  name: "ValidCode",
  components: {
    ValidCodeDisplay
  },
  data(){
    return {
      identifyCode: "", //密码登录图形验证码
      identifyCodes: "1234567890abcdefghizklmnopqrstuvwxyz", //生成图形验证码的依据
    }
  },
  methods:{
    // 刷新验证码
    refreshIdentifyCode() {
      this.identifyCode = "";
      this.makeIdentifyCode(4);
      this.rbIdentifyCode();
    },
    // 生成4位数的随机验证码
    makeIdentifyCode(l) {
      for (let i = 0; i < l; i++) {
        this.identifyCode +=
            this.identifyCodes[this.randomNum(0, this.identifyCodes.length)];
      }
    },
    // 生成单个验证码
    randomNum(min, max) {
      return Math.floor(Math.random() * (max - min) + min);
    },
    rbIdentifyCode() {
      this.$emit('update:value', this.identifyCode);
    }
  },
  mounted() {
    // 初始化验证码
    this.identifyCode = "";
    this.makeIdentifyCode(4);
    this.rbIdentifyCode();
  },
}
</script>

<template>
  <div>
    <ValidCodeDisplay :identifyCode="identifyCode" @click.native="refreshIdentifyCode">
    </ValidCodeDisplay>
  </div>
</template>

<style scoped>

</style>