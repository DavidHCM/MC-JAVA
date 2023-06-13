package net.minecraft.client.gui.components;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractScrollWidget extends AbstractWidget implements Renderable, GuiEventListener {
   private static final int BORDER_COLOR_FOCUSED = -1;
   private static final int BORDER_COLOR = -6250336;
   private static final int BACKGROUND_COLOR = -16777216;
   private static final int INNER_PADDING = 4;
   private double scrollAmount;
   private boolean scrolling;

   public AbstractScrollWidget(int p_240025_, int p_240026_, int p_240027_, int p_240028_, Component p_240029_) {
      super(p_240025_, p_240026_, p_240027_, p_240028_, p_240029_);
   }

   public boolean mouseClicked(double p_240170_, double p_240171_, int p_240172_) {
      if (!this.visible) {
         return false;
      } else {
         boolean flag = this.withinContentAreaPoint(p_240170_, p_240171_);
         boolean flag1 = this.scrollbarVisible() && p_240170_ >= (double)(this.getX() + this.width) && p_240170_ <= (double)(this.getX() + this.width + 8) && p_240171_ >= (double)this.getY() && p_240171_ < (double)(this.getY() + this.height);
         if (flag1 && p_240172_ == 0) {
            this.scrolling = true;
            return true;
         } else {
            return flag || flag1;
         }
      }
   }

   public boolean mouseReleased(double p_239063_, double p_239064_, int p_239065_) {
      if (p_239065_ == 0) {
         this.scrolling = false;
      }

      return super.mouseReleased(p_239063_, p_239064_, p_239065_);
   }

   public boolean mouseDragged(double p_239639_, double p_239640_, int p_239641_, double p_239642_, double p_239643_) {
      if (this.visible && this.isFocused() && this.scrolling) {
         if (p_239640_ < (double)this.getY()) {
            this.setScrollAmount(0.0D);
         } else if (p_239640_ > (double)(this.getY() + this.height)) {
            this.setScrollAmount((double)this.getMaxScrollAmount());
         } else {
            int i = this.getScrollBarHeight();
            double d0 = (double)Math.max(1, this.getMaxScrollAmount() / (this.height - i));
            this.setScrollAmount(this.scrollAmount + p_239643_ * d0);
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean mouseScrolled(double p_239308_, double p_239309_, double p_239310_) {
      if (!this.visible) {
         return false;
      } else {
         this.setScrollAmount(this.scrollAmount - p_239310_ * this.scrollRate());
         return true;
      }
   }

   public boolean keyPressed(int p_276060_, int p_276046_, int p_276030_) {
      boolean flag = p_276060_ == 265;
      boolean flag1 = p_276060_ == 264;
      if (flag || flag1) {
         double d0 = this.scrollAmount;
         this.setScrollAmount(this.scrollAmount + (double)(flag ? -1 : 1) * this.scrollRate());
         if (d0 != this.scrollAmount) {
            return true;
         }
      }

      return super.keyPressed(p_276060_, p_276046_, p_276030_);
   }

   public void renderWidget(PoseStack p_239793_, int p_239794_, int p_239795_, float p_239796_) {
      if (this.visible) {
         this.renderBackground(p_239793_);
         enableScissor(this.getX() + 1, this.getY() + 1, this.getX() + this.width - 1, this.getY() + this.height - 1);
         p_239793_.pushPose();
         p_239793_.translate(0.0D, -this.scrollAmount, 0.0D);
         this.renderContents(p_239793_, p_239794_, p_239795_, p_239796_);
         p_239793_.popPose();
         disableScissor();
         this.renderDecorations(p_239793_);
      }
   }

   private int getScrollBarHeight() {
      return Mth.clamp((int)((float)(this.height * this.height) / (float)this.getContentHeight()), 32, this.height);
   }

   protected void renderDecorations(PoseStack p_239981_) {
      if (this.scrollbarVisible()) {
         this.renderScrollBar(p_239981_);
      }

   }

   protected int innerPadding() {
      return 4;
   }

   protected int totalInnerPadding() {
      return this.innerPadding() * 2;
   }

   protected double scrollAmount() {
      return this.scrollAmount;
   }

   protected void setScrollAmount(double p_240207_) {
      this.scrollAmount = Mth.clamp(p_240207_, 0.0D, (double)this.getMaxScrollAmount());
   }

   protected int getMaxScrollAmount() {
      return Math.max(0, this.getContentHeight() - (this.height - 4));
   }

   private int getContentHeight() {
      return this.getInnerHeight() + 4;
   }

   private void renderBackground(PoseStack p_240049_) {
      int i = this.isFocused() ? -1 : -6250336;
      fill(p_240049_, this.getX(), this.getY(), this.getX() + this.width, this.getY() + this.height, i);
      fill(p_240049_, this.getX() + 1, this.getY() + 1, this.getX() + this.width - 1, this.getY() + this.height - 1, -16777216);
   }

   private void renderScrollBar(PoseStack p_265033_) {
      int i = this.getScrollBarHeight();
      int j = this.getX() + this.width;
      int k = this.getX() + this.width + 8;
      int l = Math.max(this.getY(), (int)this.scrollAmount * (this.height - i) / this.getMaxScrollAmount() + this.getY());
      int i1 = l + i;
      fill(p_265033_, j, l, k, i1, -8355712);
      fill(p_265033_, j, l, k - 1, i1 - 1, -4144960);
   }

   protected boolean withinContentAreaTopBottom(int p_239943_, int p_239944_) {
      return (double)p_239944_ - this.scrollAmount >= (double)this.getY() && (double)p_239943_ - this.scrollAmount <= (double)(this.getY() + this.height);
   }

   protected boolean withinContentAreaPoint(double p_239607_, double p_239608_) {
      return p_239607_ >= (double)this.getX() && p_239607_ < (double)(this.getX() + this.width) && p_239608_ >= (double)this.getY() && p_239608_ < (double)(this.getY() + this.height);
   }

   protected abstract int getInnerHeight();

   protected abstract boolean scrollbarVisible();

   protected abstract double scrollRate();

   protected abstract void renderContents(PoseStack p_239198_, int p_239199_, int p_239200_, float p_239201_);
}