<!--Presentation component for displaying product at product lists (using in /products and /favorite)-->
<script setup>
const router = useRouter()
const props = defineProps(['id', 'name', 'price', 'discountPrice', 'measureUnit', 'measureValue', 'pricePerMeasure'])
const utils = useUtils();
</script>

<template>
  <div class="flex flex-col justify-between" id="item"
       @click="() => router.push(`/product/${id}`)">
    <img :src="`http://localhost:8080/api/products/image?id=${props.id}`" alt="image"/>
    <p class="h-full text-2xl px-2">{{ props.name }}</p>
    <p class="text-2xl text-wrap px-2">
      <span v-if="props.discountPrice" class="text-red-600">{{ props.discountPrice }} zł</span>
      <span v-if="props.discountPrice" class="line-through">{{ props.price }} zł</span>
      <span v-else>{{ props.price }} zł</span>
    </p>
    <p class="text-xs px-2">
      {{ utils.pricePerMeasureText(props.measureUnit, props.pricePerMeasure) }}
    </p>
  </div>
</template>

<style scoped>
#item {
  width: 300px;
  height: 350px;
}

#item:hover {
  background: gray;
}

img {
  width: 300px;
  height: 200px;
}
</style>

