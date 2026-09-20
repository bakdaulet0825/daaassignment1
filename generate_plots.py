import matplotlib.pyplot as plt
import pandas as pd

df = pd.read_csv('results.csv')

plt.style.use('seaborn-v0_8-whitegrid' if 'seaborn-v0_8-whitegrid' in plt.style.available else 'default')
fig_size = (8, 5)

for input_type in df['input'].unique():
    plt.figure(figsize=fig_size)
    subset = df[df['input'] == input_type]
    for algo in subset['algorithm'].unique():
        algo_data = subset[subset['algorithm'] == algo]
        plt.plot(algo_data['n'], algo_data['time_ms'], marker='o', label=algo)

    plt.xscale('log')
    plt.yscale('log')
    plt.title(f'Time vs N ({input_type.capitalize()} Input)')
    plt.xlabel('Array Size (N)')
    plt.ylabel('Time (ms)')
    plt.legend()
    plt.tight_layout()
    plt.savefig(f'plot_time_{input_type}.png')
    plt.close()

for input_type in df['input'].unique():
    plt.figure(figsize=fig_size)
    subset = df[df['input'] == input_type]
    for algo in subset['algorithm'].unique():
        algo_data = subset[subset['algorithm'] == algo]
        plt.plot(algo_data['n'], algo_data['max_depth'], marker='s', label=algo)

    plt.xscale('log')
    plt.title(f'Max Recursion Depth vs N ({input_type.capitalize()} Input)')
    plt.xlabel('Array Size (N)')
    plt.ylabel('Max Recursion Depth')
    plt.legend()
    plt.tight_layout()
    plt.savefig(f'plot_depth_{input_type}.png')
    plt.close()

import numpy as np

df['ratio'] = np.where(
    df['algorithm'] == 'QuickSelect',
    df['comparisons'] / df['n'],
    df['comparisons'] / (df['n'] * np.log2(df['n']))
)

for input_type in df['input'].unique():
    plt.figure(figsize=fig_size)
    subset = df[df['input'] == input_type]
    for algo in subset['algorithm'].unique():
        algo_data = subset[subset['algorithm'] == algo]
        plt.plot(algo_data['n'], algo_data['ratio'], marker='^', label=algo)

    plt.xscale('log')
    plt.title(f'Cost Ratio vs N ({input_type.capitalize()} Input)')
    plt.xlabel('Array Size (N)')
    plt.ylabel('Ratio')
    plt.legend()
    plt.tight_layout()
    plt.savefig(f'plot_ratio_{input_type}.png')
    plt.close()

print("Plots successfully generated and saved in project directory.")