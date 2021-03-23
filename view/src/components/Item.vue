<template>
  <div class="row valign-wrapper item" v-on:click="check" :id="info.id + '-container'">
    <div class="col s1 ">
      <p>
        <label>
          <input type="checkbox" :id="info.id" class="filled-in" v-model="checkBox"/>
          <span></span>
        </label>
      </p>
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
  name: 'POIListItem',
  mounted () {
  },
  methods: {
    check: function (event) {
      document.getElementById(String(this.info.id)).checked ^= 1
      if (document.getElementById(String(this.info.id)).checked) {
        document.getElementById(this.info.id + '-container').style.backgroundColor = '#26a69a'
      } else {
        document.getElementById(this.info.id + '-container').style.backgroundColor = 'white'
      }
    }
  },
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
