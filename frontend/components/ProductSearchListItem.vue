<!--Presentation component for displaying product in search results-->
<script setup>
const router = useRouter()
const props = defineProps(['id', 'name', 'price', 'discountPrice'])
</script>

<template>
  <div class="flex items-center justify-between my-20"
       id="item"
       @click="() => router.push(`/product/${id}`)"
  >
    <img :src="`http://localhost:8080/api/products/image?id=${props.id}`" alt="image" class="block"/>
    <p class="text-2xl w-max">{{ props.name }}</p>
    <p class="text-2xl text-wrap px-2">
      <span v-if="props.discountPrice" class="text-red-600">{{ props.discountPrice }} zł</span>
      <span v-if="props.discountPrice" class="line-through">{{ props.price }} zł</span>
      <span v-else>{{ props.price }} zł</span>
    </p>
  </div>
</template>

<style scoped>
img {
  width: 100px;
  height: 100px;
}

#item:hover {
  background-color: gray;
}
</style>