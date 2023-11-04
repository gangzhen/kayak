<script>
export default {
  name: "UserInformationView",
  data() {
    return {
      userInfo: JSON.parse(localStorage.getItem("userInfo") || '{}')
    }
  },
  created() {
  },
  methods: {

  },
}
</script>

<template>
  <div>
    <div class="p-main-area">

    </div>
  </div>
</template>

<style scoped>

</style>