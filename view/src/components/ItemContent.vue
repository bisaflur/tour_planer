<template>
  <div class="row valign-wrapper item" :id="info.id + '-container'">
    <div class="col s1 ">
    </div>
    <div class="col s3">
      <img src="../assets/logo.png">
    </div>
    <div class="col s8">
      <div class="row">
        <p>{{info.category}}</p>
      </div>
      <div class="row">
        <h1>{{ info.name }}</h1>
      </div>
      <div class="row" v-if="!info.address.includes('null')">
        <p>{{info.address}}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ItemContent',
  props: {
    info: Object
  }
}
</script>

<style scoped>
.item{
  background-color: white;
  margin-top: 20px;
}
</style>
