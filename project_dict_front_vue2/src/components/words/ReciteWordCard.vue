<template>
  <el-card
  >
    <div class="layout__flex-row__left__center">
      <el-button
        size="mini"
        class="page-recite__add-btn"
        :icon="mode === 'add' ? 'el-icon-plus' : 'el-icon-minus'"
        circle
        @click="modifyNoteBook"
        title="加入单词本"
      >
      </el-button>
      <div class="page-recite__word-content">
        <div class="word__word">{{ wordInfo.word }}</div>
        <div class="word__exp" v-if="wordInfo.exp">{{ wordInfo.exp }}</div>
        <div class="word__exp" v-if="wordInfo.lastTime">上次背诵时间 {{ wordInfo.lastTime | time }}</div>
      </div>
    </div>
  </el-card>
</template>

<script>
const ReciteWordCard = {
  name: "ReciteWordCard",
  props: {
    wordInfo: {
      type: Object,
      required: true
    },
    mode: {
      default: 'add' // add remove
    }
  },
  methods: {
    modifyNoteBook () {
      if (this.mode === 'add') { // 添加
        this.$emit('word:add')
      } else if (this.mode === 'remove') { // 移除
        this.$emit('word:remove')
      }
    }
  }
}
export default ReciteWordCard
</script>

<style lang="scss" scoped>
@import "~@/styles/cus/word.scss";
</style>