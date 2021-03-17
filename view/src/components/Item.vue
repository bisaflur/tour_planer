<template>
  <div class="row valign-wrapper item" v-on:click="check">
    <div class="col s1 ">
      <p>
        <label>
          <input type="checkbox" id="test" class="filled-in" v-model="checkBox"/>
          <span></span>
        </label>
      </p>
    </div>
    <div class="col s3">
    <img src="../assets/logo.png">
    </div>
    <div class="col s8">
      <div class="row">
        <p>Category</p>
      </div>
      <div class="row">
      <h1>Title of the point of interest</h1>
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
      document.getElementById('test').checked ^= 1
    }
  }
}
</script>

<style scoped>
.item{
  background-color: white;
  margin-top: 20px;
}
</style>
