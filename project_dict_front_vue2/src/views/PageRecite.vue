<template>
  <div class="page-recite cus-page">
    <h1 class="recite-search">Search...</h1>
    <div class="layout__flex-row__space-between__center">
      <el-input
        style="flex: 1;"
        v-model="kw"
        placeholder="查询关键字"
        @change="kwChangeHandler"
      />
      <el-button
        style="margin-left: 15px;"
        icon="el-icon-notebook-1"
        circle
        @click="showNoteBook"
        type="primary"
        title="查看单词本"
      ></el-button>
    </div>

    <template v-if="wordList.length">
      <el-divider></el-divider>
      <recite-word-card
        class="recite-word-card"
        v-for="(item) in wordList"
        :key="item.id"
        mode="add"
        :word-info="{ wordId: item.id, exp: item.exp, word: item.word, lastTime: null }"
        @word:add="addWordHandler(item)"
      />
    </template>
  </div>
</template>

<script>
import wordsApi from '@/api/WordsApi' // 引入了 this.page
import ReciteWordCard from '@/components/words/ReciteWordCard'
import userWordOxfordMergeApi from "@/api/UserWordOxfordMergeApi";

const PageRecite = {
  name: 'PageRecite',
  components: {
    ReciteWordCard
  },
  data () {
    return {
      kw: '',
      wordList: [],
      userId: '1231565',
    }
  },
  methods: {
    /**
     * 关键词输入框改变的 handler
     */
    async kwChangeHandler (value) {
      if (!value || value.length < 2) {
        return
      }
      const res = await wordsApi.basicQuery(value)
      console.log('res is: ', res)
      this.wordList = res.data.list
    },
    /**
     * 去单词本
     */
    showNoteBook () {
      this.$router.push({
        name: 'WordNoteBook'
      })
    },
    async addWordHandler (wordInfo) {
      console.dir(userWordOxfordMergeApi)
      await userWordOxfordMergeApi.addCommon({
        userId: this.userId,
        wordId: wordInfo.id
      })
      this.$message.success("添加成功")
    }
  }
}
export default PageRecite
</script>

<style lang="scss" scoped>
@import "~@/styles/cus/word.scss";
.page-recite {
  .recite-search {
    text-align: center;
  }
}
</style>