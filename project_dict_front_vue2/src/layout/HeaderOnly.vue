<template>
  <div class="layout__header-only">
    <div class="layout__header layout__flex-row__space-between__center">
      <div class="layout__header-logo">{{ greeting }}，Ass</div>
      <div class="layout__header-time">{{ nowTime }}</div>
    </div>
    <router-view />
  </div>
</template>

<script>
import $util from '@/util/util'
import moment from 'moment'

export default {
  name: 'HeaderOnly',
  data () {
    return {
      greeting: '', // greeting words
      nowTime: $util.momentDefaultFormat(new moment()),
      intervalId: 0,
    }
  },
  created () {
    this.greeting = $util.getGreetingNote()
    this.nowTime = moment(new Date()).format('YYYY-MM-DD HH:MM:SS')
    this.intervalId = setInterval(() => {
      this.nowTime = $util.momentDefaultFormat(new moment())
    }, 1000)
  },
  methods: {},
}
</script>

<style lang="scss" scoped>
.layout__header-only {
  .layout__header {
    padding: 0 20px;
    height: 80px;
    background-color: lightblue;
  }
}
</style>