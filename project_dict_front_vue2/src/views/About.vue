<template>
  <div class="about">
    <h1>This is an about page</h1>
    <button @click="throwError">抛出正常错误</button>
    <button @click="throwHttpError">抛出http错误</button>
    <button @click="throwFormError">抛出form错误</button>
    <button @click="divideZeroError">除0错误</button>
    <el-divider></el-divider>
    <el-form ref="testForm" :model="formData" :rules="formRules">
      <el-form-item label="测试表单域" prop="username">
        <el-input v-model="formData.username"></el-input>
      </el-form-item>
      <div>
        <el-button @click="submit">提交</el-button>
      </div>
    </el-form>
  </div>
</template>

<script>
import HttpError from '@/util/exception/HttpError'
import FormValidationError from '@/util/exception/FormValidationError'
import $util from '@/util/util'

export default {
  data () {
    return {
      formData: {
        username: ''
      },
      formRules: {
        username: [
          {
            validator: (rule, value, cb) => {
              console.log('rule is: ', rule)
              if (value.length > 5) {
                return cb(new FormValidationError('用户名太长了'))
              }
              return cb()
            }
          }
        ]
      }
    }
  },
  methods: {
    async submit () {
      // await this.$refs.testForm.validate()
      await $util.formValidate(this.$refs.testForm)
      console.log('通过验证')
    },
    throwError () {
      throw new Error('出错了小傻子')
    },
    throwHttpError () {
      throw new HttpError('测试 http 错误，接口测试', 500)
    },
    throwFormError () {
      throw new FormValidationError('测试表单验证错误')
    },
    divideZeroError () {
      const a = 100 / 0
      const b = {}
      console.log('a', a)
      console.log(b.c.d.e)
    }
  }
}
</script>

