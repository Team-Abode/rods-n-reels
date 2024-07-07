package com.teamabode.rodsnreels.datagen.server;

import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.core.registry.RNRItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

public class RNRRecipesProvider extends FabricRecipeProvider {
    public RNRRecipesProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public static <T extends AbstractCookingRecipe> void simpleCookingRecipe(RecipeOutput exporter, String process, RecipeSerializer<T> serializer, AbstractCookingRecipe.Factory<T> factory, int cookingTime, ItemLike input, ItemLike output, float experience) {
        SimpleCookingRecipeBuilder.generic(Ingredient.of(input), RecipeCategory.FOOD, output, experience, cookingTime, serializer, factory).unlockedBy(RecipeProvider.getHasName(input), RecipeProvider.has(input)).save(exporter, RodsNReels.id(RecipeProvider.getItemName(output) + "_from_" + process));
    }

    @Override
    public void buildRecipes(RecipeOutput exporter) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(RNRItems.SQUID), RecipeCategory.FOOD, RNRItems.COOKED_SQUID, 0.35F, 200).unlockedBy(getHasName(RNRItems.SQUID), has(RNRItems.SQUID)).save(exporter);
        simpleCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100, RNRItems.SQUID, RNRItems.COOKED_SQUID, 0.35F);
        simpleCookingRecipe(exporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, 600, RNRItems.SQUID, RNRItems.COOKED_SQUID, 0.35F);
    }
}
